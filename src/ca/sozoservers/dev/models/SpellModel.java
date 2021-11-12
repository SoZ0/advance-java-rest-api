package ca.sozoservers.dev.models;

import ca.sozoservers.dev.database.models.DatabaseModel;
import ca.sozoservers.dev.database.models.DatabaseModel.Table;

@Table("Cantrips")
public class SpellModel extends DatabaseModel {

    @Constraints("PRIMARY KEY")
    @DataType("INTEGER")
    public int id;

    @DataType("TEXT")
    public String name;

    @DataType("TEXT")
    public String school;
    
    @DataType("TEXT")
    public String castingTime;

    @DataType("TEXT")
    public String range;

    @DataType("TEXT")
    public String durration;

    @DataType("TEXT")
    public String components;

}
