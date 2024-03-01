package squareonex.evetrackerdata.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "blueprints")
public class Blueprint extends Item {
    @OneToMany(mappedBy = "id.blueprint", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<BlueprintAction> actions = new HashSet<>();
    @OneToMany(mappedBy = "blueprint", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<BlueprintCopy> copies = new HashSet<>();
    @OneToMany(mappedBy = "blueprint", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<BlueprintOriginal> originals = new HashSet<>();
    public Blueprint(Long id, String name) {
        super(id, name);
    }

    public void setCopies(Set<BlueprintCopy> copies) {
        for (BlueprintCopy copy : copies) {
            copy.setBlueprint(this);
        }
        this.copies = copies;
    }

    public void setOriginals(Set<BlueprintOriginal> originals) {
        for (BlueprintOriginal original : originals) {
            original.setBlueprint(this);
        }
        this.originals = originals;
    }

    public void setActions(Set<BlueprintAction> actions) {
        for (BlueprintAction action : actions) {
            action.setBlueprint(this);
        }
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Blueprint{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", published=" + this.getPublished() +
                ", avgCost=" + this.getAvgCost() +
                '}';
    }
}
