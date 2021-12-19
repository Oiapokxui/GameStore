package usp.each.bd1.gamestore.data.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "JOGOS_MIDIA_FISICA")
public class VideoGame {
    @Id
    @Column(name = "CODIGO_DE_BARRAS")
    @Getter @Setter
    private String barcode;

    @Column(name = "DESENVOLVEDOR")
    @Getter @Setter
    private String developer;

    @Column(name = "FRANQUIA")
    @Getter @Setter
    private String franchise;

    @Column(name = "GENERO")
    @Getter @Setter
    private String genre;

    @Column(name = "DATA_DE_LANCAMENTO")
    @Getter @Setter
    private Date date;

    @Column(name = "PUBLICADORA")
    @Getter @Setter
    private String publisher;

    @Column(name = "PLATAFORMA")
    @Getter @Setter
    private String platform;

    @OneToOne
    @MapsId
    @JoinColumn(name = "CODIGO_DE_BARRAS")
    private Item item;
}
