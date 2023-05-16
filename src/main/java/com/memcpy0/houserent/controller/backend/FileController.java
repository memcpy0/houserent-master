package com.memcpy0.houserent.controller.backend;

import com.memcpy0.houserent.base.BaseController;
import com.memcpy0.houserent.constant.Constant;
import com.memcpy0.houserent.dto.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * 文件上传控制器
 */

@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    /**
     * 文件上传方法
     */
    private static Map<String, String> upload(MultipartFile file) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        String os = System.getProperty("os.name");
        File mediaPath = null;
        if (os.toLowerCase().startsWith("win")) { // 在本地机器
            mediaPath = new File(System.getProperty("user.dir") + "/src/main/resources/static/assets/img/uploads/");
            if (!mediaPath.exists()) {
                mediaPath.mkdirs();
                if (!mediaPath.mkdirs()) {
                    throw new Exception("文件上传失败，因为无法创建文件夹");
                }
            }
        } else {
            mediaPath = new File(Constant.UPLOADS_ABSOLUTE_PATH);
            if (!mediaPath.exists()) {
                mediaPath.mkdirs();
                if (!mediaPath.mkdirs()) {
                    throw new Exception("文件上传失败，因为无法创建文件夹");
                }
            }
        }

        // 原始文件名
        String originFileName = file.getOriginalFilename();
        // 后缀
        String fileSuffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        // 新文件名
        String nameWithOutSuffix = UUID.randomUUID().toString().replace("_", "");
        // 带后缀的新文件名
        String newFileName = nameWithOutSuffix + "." + fileSuffix;
        // 上传
        File descFile = new File(mediaPath.getAbsoluteFile(), newFileName);
        file.transferTo(descFile);
        // 映射路径
        String filePath = Constant.UPLOADS_PATH + newFileName;
        resultMap.put("fileName", originFileName);
        resultMap.put("filPath", filePath);
        resultMap.put("fileSuffix", fileSuffix);
        return resultMap;
    }

    /**
     * 文件上传的方法
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("key") String key,
            HttpSession session) {
        Map<String, String> map = new HashMap<>();
        try {
            map = upload(file);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("上传失败！");
        }
        String filePath = map.get("filPath");
        // 将图片url存在session中
        String sessionKey = Constant.SESSION_IMG_PREFIX + key;
        List<String> imgList = (List<String>) session.getAttribute(sessionKey);
        if (imgList == null) { // 将一个房子的所有轮播图片路径存入一个List<String>中,并存放在Sessino中
            imgList = new ArrayList<>();
        }
        imgList.add(filePath);
        session.setAttribute(sessionKey, imgList);
        return JsonResult.success("上传成功");
    }
}
