package vn.oceantech.mita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.oceantech.mita.domain.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
