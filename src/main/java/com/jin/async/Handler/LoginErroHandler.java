package com.jin.async.Handler;

import com.jin.async.EnventHandler;
import com.jin.async.EnventModel;
import com.jin.async.EnventType;
import com.jin.utils.TemplateSendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JINS on 2018/3/6.
 */
@Component
public class LoginErroHandler implements EnventHandler{


    private static final Logger logger = LoggerFactory.getLogger(LoginErroHandler.class);

    @Autowired
    TemplateSendEmail sendEmail;

    @Override
    public void doHandler(EnventModel enventModel) {
        try {

        boolean flag = sendEmail.sendMail(enventModel.getExt("name"),enventModel.getExt("erroTime"));
        }catch (Exception e){
            logger.error("发送邮箱失败"+e.getMessage());
            System.out.println("发送邮箱失败"+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<EnventType> getSupportEnventTypes() {
            return Arrays.asList(EnventType.LOGINERRO);
    }
}
