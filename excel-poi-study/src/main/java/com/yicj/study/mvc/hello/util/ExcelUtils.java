package com.yicj.study.mvc.hello.util;

import com.yicj.study.mvc.hello.annotation.ExcelAttribute;
import com.yicj.study.mvc.hello.entity.DogeInfo;
import com.yicj.study.mvc.hello.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static Workbook exportExcelByMaps(Map<String, List<?>> datas) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //遍历Map
        for (Map.Entry<String, List<?>> map : datas.entrySet()) {
            List<?> list = map.getValue();
            //将key值设为sheet名称
            HSSFSheet sheet = hssfWorkbook.createSheet(map.getKey());
            //判断map的值是否为空
            if (null == map.getValue() || map.getValue().size() == 0)
                continue;
            //使用反射抓取list的泛型
            Type type = list.getClass().getGenericSuperclass();
            ParameterizedType p = (ParameterizedType) type;
            Class cls = (Class) p.getActualTypeArguments()[0];
            //可能有即便list可能传递为空，也需要导出到表格的情况发生，所以弃置定位取类型的方法
            //Field[] fields = list.get(0).getClass().getDeclaredFields();
            Field[] fields = cls.getDeclaredFields();
            Row titleRow = sheet.createRow(0);
            for (int i = 0, m = fields.length; i < m; i++) {
                //判断该属性是否是需要导出的列，不是则直接跳过
                if (!fields[i].isAnnotationPresent(ExcelAttribute.class)) {
                    continue;
                }
                //获取属性的对应注解
                ExcelAttribute excelAttribute = fields[i].getAnnotation(ExcelAttribute.class);
                if (excelAttribute.isExport()) {
                    int colum = excelAttribute.column() - 1;
                    Cell cell = titleRow.createCell(colum);
                    cell.setCellValue(excelAttribute.name());
                    if (excelAttribute.isAdaptive()) {
                        sheet.autoSizeColumn(colum);
                    }
                }
            }
            //遍历list
            int startIndex = 0;
            int endIndex = list.size();
            Row row = null;
            Cell cell = null;
            for (int i = startIndex; i < endIndex; i++) {
                row = sheet.createRow(i + 1 - startIndex);
                //如果list为空或者size为0则跳过
                if (null == list ||list.size() == 0 ) {
                    continue;
                }
                Object o = list.get(i);
                for (int m = 0; m < fields.length; m++) {
                    Field field = fields[m];
                    if (!field.isAnnotationPresent(ExcelAttribute.class)) {
                        continue;
                    }
                    field.setAccessible(true);
                    ExcelAttribute attr = field.getAnnotation(ExcelAttribute.class);
                    try {
                        //判断该属性在现有需求下是否需要导出
                        if (attr.isExport()) {
                            //让列的宽度自适应
                            if (attr.isAdaptive()) {
                                sheet.autoSizeColumn(attr.column() - 1);
                            }
                            cell = row.createCell(attr.column() - 1);
                            cell.setCellValue(field.get(o) == null ? "" : String.valueOf(field.get(o)));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return hssfWorkbook;
    }

    public static void export2Response(Workbook workbook, String fileName, HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String((fileName + ".xls").getBytes("GBK"), "iso-8859-1"));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        Map<String, List<?>> temp = new HashMap<>();
        UserInfo tempUser1 = new UserInfo("alien","11","1","clone");
        UserInfo tempUser2 = new UserInfo("寿限无寿限无EWQEWQEWQEWQE","2222222","22","dsadsa");
        UserInfo tempUser3 = new UserInfo("dwqdwqdw","222","2","2");
        DogeInfo dogeInfo1 = new DogeInfo("alien","11","1","clone");
        DogeInfo dogeInfo12 = new DogeInfo("寿限无寿限无EWQEWQEWQEWQE","2222222","22","dsadsa");
        DogeInfo dogeInfo13 = new DogeInfo("dwqdwqdw","222","2","2");
        List<UserInfo> users = new ArrayList<UserInfo>(){{add(tempUser1);add(tempUser2);add(tempUser3);}};
        List<DogeInfo> dogeInfos = new ArrayList<DogeInfo>(){{add(dogeInfo13);add(dogeInfo12);add(dogeInfo1);}};
        List<DogeInfo> dogeInfos1 = new ArrayList<>();
        temp.put("user",users);
        temp.put("doge",dogeInfos);
        temp.put("doge2",dogeInfos1);
        temp.put("doge3",null);
        Workbook workbook = ExcelUtils.exportExcelByMaps(temp);
        return  workbook;
    }
    public static void main(String[] args){
        System.out.print(args.toString());
        Workbook workbook = exportExcel();
        ExcelUtils.export2Path(workbook,"temp","D:/File/");
    }
}
