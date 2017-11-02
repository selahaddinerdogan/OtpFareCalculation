package com.selahaddin.otpfare;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lenova
 */
import com.selahaddin.service.xmlParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathExpressionException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

@RestController
public class Controller {

    @RequestMapping("/fare")
    public String index(@RequestParam("url") String url,
            @RequestParam("toPlace") String toPlace,
            @RequestParam("time") String time,
            @RequestParam("date") String date,
            @RequestParam("mode") String mode,
            @RequestParam("maxWalkDistance") String maxWalkDistance,
            @RequestParam("arriveBy") String arriveBy,
            @RequestParam("wheelchair") String wheelchair,
            @RequestParam("locale") String locale) {
        //http://35.195.114.202:8080/otp/routers/default/plan?fromPlace=41.043627491573055%2C28.83464813232422&toPlace=40.99130135480306%2C29.074974060058597&time=1%3A25pm&date=11-01-2017&mode=TRANSIT%2CWALK&maxWalkDistance=804.672&arriveBy=false&wheelchair=false&locale=en
        xmlParser xp = new xmlParser();
        String param = url + "&toPlace=" + toPlace + "&time=" + time + "&date=" + date + "&mode=" + mode + "&maxWalkDistance=" + maxWalkDistance + "&arriveBy=" + arriveBy + "&wheelchair=" + wheelchair + "&locale=" + locale;
        try {
            return xp.xmlParser(param);
        } catch (SAXException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }

}
