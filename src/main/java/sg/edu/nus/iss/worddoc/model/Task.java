package sg.edu.nus.iss.worddoc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Task {

    @NotEmpty(message = "id required")
    @Size(max = 50, message = "max length 50")
    private String id;
    @NotEmpty(message = "name required")
    @Size(min = 10, max = 50, message = "between 10 and 50 characters")
    private String name;
    @Size(max = 255, message = "max length 255")
    private String description;
    @FutureOrPresent(message = "Date equal or after today")
    private Date dueDate;
    @Pattern(regexp = "Low|Medium|High", message = "must be Low, Medium, or High")
    private String priorityLevel;
    @Pattern(regexp = "Pending|Started|Progress|Completed", message = "must be Pending, Started, Progress, or Completed")
    private String status;
    private Date createdAt; // cannot be changed once record is created
    private Date updatedAt; // changed each time record is updated

    public Task(@NotEmpty(message = "id required") @Size(max = 50, message = "max length 50") String id,
            @NotEmpty(message = "name required") @Size(min = 10, max = 50, message = "between 10 and 50 characters") String name,
            @Size(max = 255, message = "max length 255") String description,
            @FutureOrPresent(message = "Date equal or after today") Date dueDate,
            @Pattern(regexp = "Low|Medium|High", message = "must be Low, Medium, or High") String priorityLevel,
            @Pattern(regexp = "Pending|Started|Progress|Completed", message = "must be Pending, Started, Progress, or Completed") String status,
            Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priorityLevel = priorityLevel;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task() {
    }

    public String simpleDueDate() {
        if (dueDate != null) {
            long epochMilliseconds = dueDate.getTime();
            Date retrievedDate = new Date(epochMilliseconds);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(retrievedDate);
        }
        return "";
    }

    public String simpleCreatedAt() {
        if (createdAt != null) {
            long epochMilliseconds = createdAt.getTime();
            Date retrievedDate = new Date(epochMilliseconds);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(retrievedDate);
        }
        return "";
    }

    public String simpleUpdatedAt() {
        if (updatedAt != null) {
            long epochMilliseconds = updatedAt.getTime();
            Date retrievedDate = new Date(epochMilliseconds);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(retrievedDate);
        }
        return "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
