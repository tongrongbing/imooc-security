package com.imooc.security.core.image;

import com.imooc.security.core.validate.core.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author：tongrongbing
 * @date：created in 2020/7/4 22:17
 * @description： 图片验证码
 */
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime dateTime) {
        super(code, dateTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }


}