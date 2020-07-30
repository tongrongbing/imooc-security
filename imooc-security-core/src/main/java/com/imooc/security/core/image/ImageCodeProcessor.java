package com.imooc.security.core.image;

import com.imooc.security.core.validate.core.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/5 16:20
 * @description： 图片验证码处理器
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}