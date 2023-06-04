package quannkph29999.fpoly.danh_ba_content_provider;

public class DanhBa {
    private int id;
    private String ten_DB;
    private String sdt_DB;

    public DanhBa(int id, String ten_DB, String sdt_DB) {
        this.id = id;
        this.ten_DB = ten_DB;
        this.sdt_DB = sdt_DB;
    }

    public DanhBa() {
    }

    public DanhBa(String ten_DB, String sdt_DB) {
        this.ten_DB = ten_DB;
        this.sdt_DB = sdt_DB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_DB() {
        return ten_DB;
    }

    public void setTen_DB(String ten_DB) {
        this.ten_DB = ten_DB;
    }

    public String getSdt_DB() {
        return sdt_DB;
    }

    public void setSdt_DB(String sdt_DB) {
        this.sdt_DB = sdt_DB;
    }
}
