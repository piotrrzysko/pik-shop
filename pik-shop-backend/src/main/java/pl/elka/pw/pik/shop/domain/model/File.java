package pl.elka.pw.pik.shop.domain.model;

import javax.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String fileName;

    public File() {

    }

    public File(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }
}
