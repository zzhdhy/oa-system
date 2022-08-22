package com.zzh.oa_system.service.user;

import com.github.pagehelper.util.StringUtil;
import com.zzh.oa_system.model.dao.processdao.NotepaperDao;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:29
 * description:
 */
@Service
@Transactional
public class NotepaperService {

    @Autowired
    private NotepaperDao ndao;

    @Value("${img.root.path}")
    private String rootpath;

    public void delete(Long id) {
        ndao.delete(id);
    }

    //@PostConstruct
    public void UserpanelController() {
        try {
            rootpath = ResourceUtils.getURL("classpath:").getPath().replace("target/classes/", "static/image");
            //System.out.println(rootpath);
        } catch (IOException e) {
            System.out.println("获取项目路径异常");
        }
    }

    /**
     * 上传头像
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    public String upload(MultipartFile file) throws IllegalStateException, IOException {

        File dir = new File(rootpath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        if (!StringUtil.isEmpty(fileName)) {

            String suffix = FilenameUtils.getExtension(fileName);

            String newFileName = UUID.randomUUID().toString().toLowerCase() + "." + suffix;
            File targetFile = new File(dir, newFileName);
            file.transferTo(targetFile);
            System.out.println(newFileName + "mmm");
            String imgpath = targetFile.getPath().replace("\\", "/").replace(rootpath, "");

            System.out.println(imgpath);

            return imgpath;
        } else {
            return null;
        }

    }
}
