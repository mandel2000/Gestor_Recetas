package models;

import java.sql.Timestamp;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

/**
 * The Class BaseModel.
 */
@MappedSuperclass
public class BaseModel extends Model {

    /** The id. */
    @Id
    private Long id;

    /** The version. */
    @Version
    private Long version;

    /** The when created. */
    @WhenCreated
    private Timestamp whenCreated;

    /** The when modified. */
    @WhenModified
    private Timestamp whenModified;

    /**
     * Instantiates a new base model.
     */
    public BaseModel() {
	super();
    }

    /**
     * Instantiates a new base model.
     *
     * @param id the id
     * @param version the version
     * @param whenCreated the when created
     * @param whenModified the when modified
     */
    public BaseModel(Long id, Long version, Timestamp whenCreated, Timestamp whenModified) {
	super();
	this.id = id;
	this.version = version;
	this.whenCreated = whenCreated;
	this.whenModified = whenModified;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion() {
	return version;
    }

    /**
     * Gets the when created.
     *
     * @return the when created
     */
    public Timestamp getWhenCreated() {
	return whenCreated;
    }

    /**
     * Gets the when modified.
     *
     * @return the when modified
     */
    public Timestamp getWhenModified() {
	return whenModified;
    }

}
