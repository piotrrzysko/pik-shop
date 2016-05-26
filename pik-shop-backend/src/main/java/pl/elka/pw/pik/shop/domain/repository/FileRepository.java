package pl.elka.pw.pik.shop.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.elka.pw.pik.shop.domain.model.File;

public interface FileRepository extends CrudRepository<File, Long> {
}
