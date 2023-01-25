package com.afriland.packageservices.controller;

import com.afriland.packageservices.dto.CBSClientDTO;
import com.afriland.packageservices.dto.OtpDTO;
import com.afriland.packageservices.entity.*;
import com.afriland.packageservices.enums.HTTPResponseMessage;
import com.afriland.packageservices.enums.PhoneNumberPattern;
import com.afriland.packageservices.enums.SubscriptionType;
import com.afriland.packageservices.model.*;
import com.afriland.packageservices.repository.OTPRepository;
import com.afriland.packageservices.repository.PackRepository;
import com.afriland.packageservices.repository.SubscriberRepository;
import com.afriland.packageservices.requirement.ISubscription;
import com.afriland.packageservices.service.CBSClientService;
import com.afriland.packageservices.service.OTPService;
import com.afriland.packageservices.service.SubscriberService;
import com.afriland.packageservices.service.SubscriptionService;
import com.afriland.packageservices.utils.ApplicationUtils;
import com.afriland.packageservices.utils.CBSUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.LifecycleState;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
@RestController
@RequestMapping("api/v1/subscription")
public class SubscriptionController implements ISubscription {

    @Autowired
    PackRepository packRepository;

    @Autowired
    SubscriberRepository subscriberRepository;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private CBSClientService cbsClientService;

    @Autowired
    private OTPService otpService;


    @Autowired
    OTPRepository otpRepository;

    private ModelMapper mapper = new ModelMapper();

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<List<Subscription>> all() {

        List<Subscription> subscriptionList = subscriptionService.findAll();

        return ResponseEntity.ok(subscriptionList);
    }


    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/subscribe")
    public ResponseEntity<Object> subscribe(@RequestParam("clientCode") String clientCode,
                                            @RequestParam("pack") UUID packId) {

        Optional<Pack> pack = packRepository.findById(packId);

        if (!pack.isPresent()) {

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("message", HTTPResponseMessage.RESOURCE_NOT_EXISTS);
            map.put("status", HTTPResponseMessage.ERROR);

            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

        List<Subscriber> subscribers = subscriberService.findByCode(clientCode);
        Subscriber subscriber = new Subscriber();

        if (subscribers.size() > 0) {
            subscriber = subscribers.get(0);
        }
        else {
            CBSClientDTO cbsClientDTO = new CBSClientDTO();

            try {
                cbsClientDTO = CBSUtils.getClient(clientCode);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally {

                subscriber.setName(cbsClientDTO.getCustomerName());
                subscriber.setCode(cbsClientDTO.getMatricule());
                subscriber.setAccountNumbers(cbsClientDTO.getComptes());
                subscriber.setPhoneNumbers(cbsClientDTO.getTelephones());

                subscriberRepository.save(subscriber);
            }
        }

        //System.out.println(subscriber);

        PackDTO packDTO = mapper.map(pack, PackDTO.class);

        Set<ProductDTO> productDTOList = packDTO.getProducts();


        Map<String, Object> response = new HashMap<>();

        for (ProductDTO productDTO : productDTOList) {

            try {
                response.put(productDTO.getCode(), productDTO.subscribe(subscriber));
            }
            catch (Exception e) {

                System.out.println(e.getMessage());
                response.put("message", HTTPResponseMessage.UNABLE_TO_PROCESS);
                response.put("status", HTTPResponseMessage.ERROR);

                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/validate")
    public ResponseEntity<SubscriptionDTO> validate() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/suspend")
    public ResponseEntity<SubscriptionDTO> suspend() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/terminate")
    public ResponseEntity<SubscriptionDTO> terminate() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/activate")
    public ResponseEntity<SubscriptionDTO> activate() {
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/validate-otp")
    public ResponseEntity<Map<String, Object>> validateOTP(@RequestParam("clientCode") String clientCode,
                                                           @RequestParam("otp") String otp) {

        System.out.println(clientCode);
        System.out.println(otp);

        CBSClient cbsClient = null;

        Map<String, Object> response = new HashMap<>();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/generate-otp")
    public ResponseEntity<Map<String, Object>> generateAndSendOTP(@RequestParam("clientCode") String clientCode,
                                                                  @RequestParam("phoneNumber") String phoneNumber) {

        System.out.println(clientCode);

        Map<String, Object> response = new HashMap<>();
        response.put("status", HTTPResponseMessage.ERROR);

        /*if (!ApplicationUtils.isValidPhoneNumber(phoneNumber, PhoneNumberPattern.NOT_CONSIDERED.getValue())) {

            response.put("message", HTTPResponseMessage.BAD_PHONE_NUMBER_FORMAT);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        List<CBSClient> cbsClients =  cbsClientService.findByCode(clientCode);

        if (cbsClients.size() < 1) {

            CBSClientDTO cbsClientDTO = CBSUtils.getClient(clientCode);

            System.out.println(cbsClientDTO);

            response.put("message", HTTPResponseMessage.RESOURCE_NOT_EXISTS);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CBSClientDTO cbsClientDTO = mapper.map(cbsClients.get(0), CBSClientDTO.class);

        //System.out.println(subscriber);


        try {
            String otpStr = Integer.toString(ApplicationUtils.generateOTP());

            String message = "Your First Bank OTP is " + otpStr;
            ApplicationUtils.sendSMS(phoneNumber, message);

            assert ApplicationUtils.sendSMS(phoneNumber, message);

            OtpDTO otpDTO = new OtpDTO();
            otpDTO.setOtp(otpStr);
            otpDTO.setCbsClient(cbsClientDTO);

            otpService.create(otpDTO);

            cbsClientDTO.getOtpList().add(otpDTO);

            CBSClient cbsClient = mapper.map(cbsClientDTO, CBSClient.class);

            cbsClientService.create(cbsClient);

            //System.out.println(subscriber);


        }
        catch (AssertionError e) {
            System.out.println(e.getMessage());


            response.put("message", HTTPResponseMessage.UNABLE_TO_SEND_OTP);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

            response.put("message", HTTPResponseMessage.UNABLE_TO_GENERATE_OTP);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }



        response.put("message", HTTPResponseMessage.OTP_SUCCESSFULLY_SEND);
        response.put("status", HTTPResponseMessage.SUCCESS);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
