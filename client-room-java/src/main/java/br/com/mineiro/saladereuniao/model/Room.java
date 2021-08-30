package br.com.mineiro.saladereuniao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="meetingroom")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)    //campo obrigatório
    private String nome;      //campo identico ao ser colocado no Postman

    @Column(name = "date", nullable = false)
    private String data;

    @Column(name = "startHour", nullable = false)
    private String horaInicio;

    @Column(name = "endHour", nullable = false)
    private String horaFim;

    @Override
    public String toString() {
        return "Room [id=" + id + ", nome=" + nome + ", data=" + data +
                ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + "]";
    }
}
