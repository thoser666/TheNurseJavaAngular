package biz.brumm.thenursejavaangular.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import biz.brumm.thenursejavaangular.model.idclasses.FollowingId;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * @author UrosVesic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(FollowingId.class)
public class Following implements MyEntity{
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "following_user_id", nullable = false)
    private User following;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "followed_user_id", nullable = false)
    private User followed;
    private Instant followedAt;



}
