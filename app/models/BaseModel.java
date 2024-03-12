package models;

import java.sql.Timestamp;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public class BaseModel extends Model {

    @Id
    private Long id;

    @Version
    private Long version;

    @WhenCreated
    private Timestamp whenCreated;

    @WhenModified
    private Timestamp whenModified;

    public Long getId() {
	return id;
    }

    public Long getVersion() {
	return version;
    }

    public Timestamp getWhenCreated() {
	return whenCreated;
    }

    public Timestamp getWhenModified() {
	return whenModified;
    }

}
