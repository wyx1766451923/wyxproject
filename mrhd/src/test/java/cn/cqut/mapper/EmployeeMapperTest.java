package cn.cqut.mapper;

import cn.cqut.domain.Employee;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testSave() throws Exception {
        Employee employee = new Employee();
//        插入
        employee.setName("懒羊羊");
        employee.setAge(15);
        employee.setPhone("11549635155");
        employeeMapper.insert(employee);


    }
    @Test
    public void testUpdate() throws Exception{
//        修改
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("喜羊羊");
        employee.setAge(17);
        employee.setPhone("19849635155");
        employeeMapper.updateById(employee);
    }
    @Test
    public void testDelete() throws Exception{
        //        删除
        Employee employee = new Employee();
        employeeMapper.deleteById(1L);
    }
    @Test
    public void testSelectById() throws Exception{
        //        查询
        Employee employeePrint = employeeMapper.selectById(2L);
        System.out.println("id:"+employeePrint.getId()+"  name:"+employeePrint.getName()+
                "  age:"+employeePrint.getAge()+"  phone:"+employeePrint.getPhone());
    }
    @Test
    public void testSelectCount() throws Exception{
        System.out.println(employeeMapper.selectCount(null));
    }
    @Test
    public void testQueryPage() throws Exception{
        Page page = new Page(1,2);
        Page page1 = employeeMapper.selectPage(page,null);
        System.out.println("当前页码:"+page1.getCurrent());
        System.out.println("每页条数:"+page1.getSize());
        System.out.println("总条数:"+page1.getTotal());
        System.out.println("数据:"+page1.getRecords());
        System.out.println("总页数:"+page1.getPages());

    }





}