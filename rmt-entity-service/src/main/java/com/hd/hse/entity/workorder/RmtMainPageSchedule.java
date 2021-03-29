package com.hd.hse.entity.workorder;

import com.hd.hse.common.entity.SuperEntity;

public class RmtMainPageSchedule extends SuperEntity {


    /**
     * all_num : 2
     * gen_num : 1
     * eight_type_num : 1
     * territorial_unit_name : 化肥一厂
     * territorial_unit_id : 2000000000106
     */

    private int all_num;
    private int gen_num;
    private int eight_type_num;
    private String territorial_unit_name;
    private String territorial_unit_id;
    private String convert_all_num;
    private String convert_gen_num;
    private String convert_eight_type_num;


    public String getConvert_all_num() {
        return convert_all_num;
    }

    public void setConvert_all_num(String convert_all_num) {
        this.convert_all_num = convert_all_num;
    }

    public String getConvert_gen_num() {
        return convert_gen_num;
    }

    public void setConvert_gen_num(String convert_gen_num) {
        this.convert_gen_num = convert_gen_num;
    }

    public String getConvert_eight_type_num() {
        return convert_eight_type_num;
    }

    public void setConvert_eight_type_num(String convert_eight_type_num) {
        this.convert_eight_type_num = convert_eight_type_num;
    }

    public int getAll_num() {
        return all_num;
    }

    public void setAll_num(int all_num) {
        this.all_num = all_num;
    }

    public int getGen_num() {
        return gen_num;
    }

    public void setGen_num(int gen_num) {
        this.gen_num = gen_num;
    }

    public int getEight_type_num() {
        return eight_type_num;
    }

    public void setEight_type_num(int eight_type_num) {
        this.eight_type_num = eight_type_num;
    }

    public String getTerritorial_unit_name() {
        return territorial_unit_name;
    }

    public void setTerritorial_unit_name(String territorial_unit_name) {
        this.territorial_unit_name = territorial_unit_name;
    }

    public String getTerritorial_unit_id() {
        return territorial_unit_id;
    }

    public void setTerritorial_unit_id(String territorial_unit_id) {
        this.territorial_unit_id = territorial_unit_id;
    }
}
