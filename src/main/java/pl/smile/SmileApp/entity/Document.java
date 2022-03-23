package pl.smile.SmileApp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Lob
    private byte[] data;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Appointment appointment;

    public Document(String name, String type, byte[] data) {
        super();
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
