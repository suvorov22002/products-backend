package com.afriland.packageservices.service;

import com.afriland.packageservices.dto.OtpDTO;
import com.afriland.packageservices.entity.OTP;
import com.afriland.packageservices.repository.CBSClientRepository;
import com.afriland.packageservices.repository.OTPRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    private ModelMapper modelMapper= new ModelMapper();


    public OTP create(OtpDTO otpDTO) {

        OTP otp = modelMapper.map(otpDTO, OTP.class);

        return otpRepository.save(otp);
    }
}
