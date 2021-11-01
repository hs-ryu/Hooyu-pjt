package com.status.backend.user.service;

import com.status.backend.global.exception.NoUserException;
import com.status.backend.global.service.ResponseGenerateService;
import com.status.backend.user.dto.UserMapping;
import com.status.backend.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserServiceImpl userService;
    private final ResponseGenerateService responseGenerateService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserResponseDto getUserInfo(Long userPK) throws NoUserException {
        return null;
    }

    @Override
    public String DuplicateCheckName(String userName) throws NoUserException {
        return null;
    }

    @Override
    public String ChangeName(String userName) throws NoUserException {
        return null;
    }

    @Override
    public String ChangeEmoji(Long userPK, String userEmoji) throws NoUserException {
        return null;
    }

    @Override
    public String SetUpPrivateZone(Long userPK, BigDecimal lat, BigDecimal lon) throws NoUserException {
        return null;
    }

    @Override
    public String SetPushAlarmReceive(Long userPK, Boolean accept) throws NoUserException {
        return null;
    }

    @Override
    public String SetPushAlarmSync(Long userPK, Boolean sync) throws NoUserException {
        return null;
    }

    @Override
    public String SetPushAlarmRadius(Long userPK, int radius) throws NoUserException {
        return null;
    }

    @Override
    public List<UserMapping> getUserWithinRadius(Long userPK, BigDecimal lat, BigDecimal lon, int radius) throws NoUserException {
        return null;
    }
}
