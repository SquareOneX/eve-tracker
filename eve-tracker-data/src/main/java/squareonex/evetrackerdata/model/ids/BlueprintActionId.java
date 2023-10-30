package squareonex.evetrackerdata.model.ids;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Embeddable
public class BlueprintActionId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "blueprint_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_blueprintactions_blueprints"))
    Blueprint blueprint;
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_blueprintactions_activities"))
    Activity activity;

    public BlueprintActionId() {

    }

    public BlueprintActionId(Blueprint blueprint, Activity activity) {
        this.blueprint = blueprint;
        this.activity = activity;
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Blueprint blueprint) {
        this.blueprint = blueprint;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
