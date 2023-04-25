package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "client_id")
    private String clientId;
    private String hash;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    private Token token;

    public Client(String clientName, String hash) {
        this.clientId = clientName;
        this.hash = hash;
    }

}
