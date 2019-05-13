package cn.hncu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hncu.utils.hncuJSONResult;

@Controller
@RequestMapping("video")
public class VideoController {

	//@Autowired
	//private UsersServiceImpl usersService;

	@GetMapping("/showAddBgm")
	public String showAddBgm() {
		return "video/addBgm";
	}

	@PostMapping("/bgmUpload")
	@ResponseBody
	public hncuJSONResult bgmUpload(@RequestParam("file") MultipartFile[] files) throws Exception {

		// 文件保存的路径
		// String fileSpace = "D:" + File.separator + "hncu_videos_dev" + File.separator
		// + "mvc_bgm";
		String fileSpace = File.separator + "hncu_videos_dev" + File.separator + "mvc_bgm";
		// 保存到数据库中的相对路径
		String uploadPathDB = File.separator + "bgm";

		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;

		try {
			if (files != null && files.length > 0) {
				String fileName = files[0].getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					// 文件上传的最终保存路径
					String finalPath = fileSpace + uploadPathDB + File.separator + fileName;
					// 设置数据库保存的路径
					uploadPathDB += (File.separator + fileName);

					File outFile = new File(finalPath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						// 创建父文件夹
						outFile.getParentFile().mkdirs();
					}

					fileOutputStream = new FileOutputStream(outFile);
					inputStream = files[0].getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				}
			} else {
				return hncuJSONResult.errorMsg("上传出错...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return hncuJSONResult.errorMsg("上传出错...");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}

		return hncuJSONResult.ok(uploadPathDB);
	}
}
