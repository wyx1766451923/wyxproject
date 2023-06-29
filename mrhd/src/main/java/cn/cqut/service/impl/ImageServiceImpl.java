package cn.cqut.service.impl;

import cn.cqut.domain.Image;
import cn.cqut.mapper.ImageMapper;
import cn.cqut.service.ImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper,Image> implements ImageService {


}
