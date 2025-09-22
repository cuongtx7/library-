package com.example.demo.until;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.DecimalFormat;
@Service
public class GenerateId {

    @Transactional(readOnly = true)
    public <T> String generateId(JpaRepository<T, String> repository, String prefix) {
        long count = repository.count();
        DecimalFormat df = new DecimalFormat(prefix + "0000");
        return df.format(count + 1);
    }
}
