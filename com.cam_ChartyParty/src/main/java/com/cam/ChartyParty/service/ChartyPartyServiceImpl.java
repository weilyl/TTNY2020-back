package com.cam.ChartyParty.service;

import com.cam.ChartyParty.dao.DataNotFoundException;
import com.cam.ChartyParty.dto.Game;
import com.cam.ChartyParty.dto.Round;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.cam.ChartyParty.dao.ChartyPartyGameDao;
import com.cam.ChartyParty.dao.ChartyPartyRoundDao;

/**
 *
 * @author chelseamiller
 */
@Service
public class ChartyPartyServiceImpl {



    public String generateEntryCode() {
    }

  
    public String generateScore(String answer, String guess) throws BadUserInputException {
        
    }

  
}
