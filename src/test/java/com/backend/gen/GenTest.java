package com.backend.gen;

import com.backend.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@Slf4j
public class GenTest {
    // 实体名
    private String entity = "UserTag";
    private String obj = StringUtil.lowerCaseFirstLetter(entity);
    // 描述
    private String desc = "用户标签关联";

    // 模板文件路径
    private String ftlPath = "D:\\Code\\graduation-project\\backend\\src\\test\\java\\com\\backend\\gen\\ftl\\";
    // 生成代码的目录
    private String srcPath = "D:\\Code\\graduation-project\\backend\\src\\main\\java\\com\\backend\\";

    /**
     * 生成Service+ServiceImp+Converter+Controller
     */
    @Test
    void gen() {
        genService();
        genServiceImpl();
        genConverter();
        genController();
        System.out.println("代码生成成功！");
    }

    /**
     * 生成Service接口文件
     * <p>
     * 该方法根据指定的实体名称，生成对应的Service接口文件。
     * 模板文件为service.ftl，生成的文件名为：实体名+Service.java
     * </p>
     */
    @Test
    void genService() {
        final File ftlFile = new File(ftlPath + "service.ftl");
        if (!ftlFile.exists()) {
            log.error("模板文件不存在");
            return;
        }

        final String srcFile = srcPath + "service\\" + entity + "Service.java";
        try (final BufferedWriter bw = new BufferedWriter(new FileWriter(srcFile));
             BufferedReader br = new BufferedReader(new FileReader(ftlFile));) {
            bw.write("package com.backend.service;\r\n\r\n");
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.replace("${Entity}", entity)
                        .replace("${obj}", obj)
                        .replace("${desc}", desc);
                bw.write(line + "\r\n");
            }
            bw.flush();
        } catch (Exception e) {
            log.error("生成service失败");
        }
    }

    /**
     * 生成Service接口实现类文件
     */
    @Test
    void genServiceImpl() {
        final File ftlFile = new File(ftlPath + "serviceImpl.ftl");
        if (!ftlFile.exists()) {
            log.error("模板文件不存在");
            return;
        }

        final String srcFile = srcPath + "service\\impl\\" + entity + "ServiceImpl.java";
        try (final BufferedWriter bw = new BufferedWriter(new FileWriter(srcFile));
             BufferedReader br = new BufferedReader(new FileReader(ftlFile));) {
            bw.write("package com.backend.service.impl;\r\n\r\n");
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.replace("${Entity}", entity)
                        .replace("${obj}", obj);
                bw.write(line + "\r\n");
            }
            bw.flush();
        } catch (Exception e) {
            log.error("生成service实现类失败");
        }
    }

    /**
     * 生成类型转换器文件
     */
    @Test
    void genConverter() {
        final File ftlFile = new File(ftlPath + "converter.ftl");
        if (!ftlFile.exists()) {
            log.error("模板文件不存在");
            return;
        }

        final String srcFile = srcPath + "converter\\" + entity + "Converter.java";
        try (final BufferedWriter bw = new BufferedWriter(new FileWriter(srcFile));
             BufferedReader br = new BufferedReader(new FileReader(ftlFile));) {
            bw.write("package com.backend.converter;\r\n\r\n");
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.replace("${Entity}", entity)
                        .replace("${obj}", obj)
                        .replace("${desc}", desc);
                bw.write(line + "\r\n");
            }
            bw.flush();
        } catch (Exception e) {
            log.error("生成converter类失败");
        }
    }

    /**
     * 生成控制器文件
     */
    @Test
    void genController() {
        final File ftlFile = new File(ftlPath + "controller.ftl");
        if (!ftlFile.exists()) {
            log.error("模板文件不存在");
            return;
        }

        final String srcFile = srcPath + "controller\\" + entity + "Controller.java";
        try (final BufferedWriter bw = new BufferedWriter(new FileWriter(srcFile));
             BufferedReader br = new BufferedReader(new FileReader(ftlFile));) {
            bw.write("package com.backend.controller;\r\n\r\n");
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.replace("${Entity}", entity)
                        .replace("${obj}", obj)
                        .replace("${desc}", desc);
                bw.write(line + "\r\n");
            }
            bw.flush();
        } catch (Exception e) {
            log.error("生成controller类失败");
        }
    }
}
