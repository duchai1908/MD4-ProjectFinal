package com.ra.md4projectapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ra.md4projectapi.constants.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Double totalPrice;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String note;
    @NotBlank(message = "Receive name must not be null")
    private String receiveName;
    @NotBlank(message = "Receive address must not be null")
    private String receiveAddress;
    @NotBlank(message = "Receive phone must not be null")
    private String receivePhone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date receivedAt;
}
