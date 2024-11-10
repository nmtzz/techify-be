package app.techify.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    public Map upload(MultipartFile file) {
        Cloudinary cloudinary = new Cloudinary("cloudinary://388249677938798:WUxnO7kxyRCUVW_SBzEnxxhn_ho@daft8gwa9");
        try {
            Map data = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return data;
        } catch (IOException io) {
            throw new RuntimeException("Image upload fail");
        }
    }
}
