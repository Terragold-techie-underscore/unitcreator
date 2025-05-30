
package example;

import arc.*;
import arc.graphics.*;
import arc.math.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

public class UnitCreator extends GenericCrafter {
    public UnitCreator(String name) {
        super(name);
        
        hasItems = true;
        hasPower = true;
        itemCapacity = 50;
        craftTime = 300f;
        size = 4;
        
        consumes.power(3f);
        consumes.items(with(Items.silicon, 30, Items.titanium, 20, Items.plastanium, 10));
        
        category = Category.units;
        buildVisibility = BuildVisibility.shown;
        
        requirements(Category.units, with(Items.copper, 200, Items.lead, 150, Items.silicon, 100, Items.titanium, 80));
    }

    public class UnitCreatorBuild extends GenericCrafterBuild {
        UnitType selectedUnit = UnitTypes.dagger;
        float health = 100f;
        float speed = 1f;
        float damage = 10f;
        
        @Override
        public void buildConfiguration(Table table) {
            table.button(Icon.units, Styles.clearToggleTransi, () -> {
                Dialog dialog = new Dialog("Unit Configuration");
                dialog.cont.pane(t -> {
                    t.defaults().pad(5f);
                    t.add("Health: ").left();
                    t.field(String.valueOf(health), text -> {
                        health = Strings.parseFloat(text, 100f);
                    }).width(120f);
                    t.row();
                    t.add("Speed: ").left();
                    t.field(String.valueOf(speed), text -> {
                        speed = Strings.parseFloat(text, 1f);
                    }).width(120f);
                    t.row();
                    t.add("Damage: ").left();
                    t.field(String.valueOf(damage), text -> {
                        damage = Strings.parseFloat(text, 10f);
                    }).width(120f);
                });
                dialog.addCloseButton();
                dialog.show();
            }).size(40f);
        }

        @Override
        public void craft() {
            Unit unit = selectedUnit.create(team);
            unit.set(x, y);
            unit.health = health;
            unit.speed = speed;
            unit.damage = damage;
            unit.add();
        }
    }
}
