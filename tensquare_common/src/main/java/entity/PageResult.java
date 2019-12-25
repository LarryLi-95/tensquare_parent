package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

/**
 * @projectName:tensquare_parent
 * @packageName:entity
 * @className:PageResult
 * @author:larry
 * @date:2019/12/18 22:41
 * @description:
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PageResult <T>{
private long total;
private List<T> rows;
}
