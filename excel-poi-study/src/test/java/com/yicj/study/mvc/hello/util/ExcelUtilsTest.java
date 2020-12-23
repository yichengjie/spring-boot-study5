package com.yicj.study.mvc.hello.util;

import com.yicj.study.mvc.hello.entity.DogeInfo;
import com.yicj.study.mvc.hello.entity.UserInfo;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilsTest {
    /**
     * 将excel输出至本地路径
     */
    public static void export2Path(Workbook workbook, String fileName, String path) {
        File dir = new File(path);
        //路径不存在则创建
        if (!dir.exists() || !(dir.isDirectory())) {
            dir.mkdir();
        }
        String filePath = path.concat(fileName).concat(".xls");
        File file = new File(filePath);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            workbook.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            //关闭流
            if (null != out) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static Workbook exportExcel(){
        UserInfo tempUser1 = new UserInfo("alien","11","1","clone");
        UserInfo tempUser2 = new UserInfo("寿限无寿限无EWQEWQEWQEWQE","2222222","22","dsadsa");
        UserInfo tempUser3 = new UserInfo("dwqdwqdw","222","2","2");

        DogeInfo dogeInfo1 = new DogeInfo("alien","11","1","clone");
        DogeInfo dogeInfo12 = new DogeInfo("寿限无寿限无EWQEWQEWQEWQE","2222222","22","dsadsa");
        DogeInfo dogeInfo13 = new DogeInfo("dwqdwqdw","222","2","2");
        List<UserInfo> users = new ArrayList<UserInfo>(){{add(tempUser1);add(tempUser2);add(tempUser3);}};

        List<DogeInfo> dogeInfos = new ArrayList<DogeInfo>(){{add(dogeInfo13);add(dogeInfo12);add(dogeInfo1);}};
        List<DogeInfo> dogeInfos1 = new ArrayList<>();

        ExcelUtils.ExportExcelInfo export1 = new ExcelUtils.ExportExcelInfo("user", users, null);
        ExcelUtils.ExportExcelInfo export2 = new ExcelUtils.ExportExcelInfo("doge", dogeInfos, null);
        ExcelUtils.ExportExcelInfo export3 = new ExcelUtils.ExportExcelInfo("doge2", dogeInfos1, null);
        ExcelUtils.ExportExcelInfo export4 = new ExcelUtils.ExportExcelInfo("doge3", null, null);
        List<ExcelUtils.ExportExcelInfo> exports = new ArrayList<>() ;
        exports.add(export1) ;
        exports.add(export2) ;
        exports.add(export3) ;
        exports.add(export4) ;
        Workbook workbook = ExcelUtils.exportExcel(exports);
        return  workbook;
    }

    public static void main(String[] args){
        System.out.print(args.toString());
        Workbook workbook = exportExcel();
        export2Path(workbook,"temp","D:\\opt\\test\\");
    }
}
