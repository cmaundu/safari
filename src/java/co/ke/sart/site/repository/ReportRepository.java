/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.repository;

import co.ke.sart.site.entity.ListOfValue;
import co.ke.sart.site.entity.PaymentSummary;
import co.ke.sart.site.model.ChargeDisplay;
import co.ke.sart.site.model.OpenAttendanceDetails;
import co.ke.sart.site.model.PrescriptionReport;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository("reportRepository")
public class ReportRepository {

    SimpleJdbcCall procCall;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public List<ChargeDisplay> getChargeMatrix(String lovType) {
        Map in = new HashMap();
        in.put("vlovtype", lovType);
        procCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sart_proc_charges")
                //.withSchemaName("safari")
                .withoutProcedureColumnMetaDataAccess()
                .useInParameterNames("vlovType")
                .declareParameters(new SqlParameter("vlovtype", Types.VARCHAR))
                .returningResultSet("result", (rs, i) -> {
                    return mapChargeMatrixResultRow(rs);
                });
        SqlParameterSource inParams = new MapSqlParameterSource(in);
        Map m = procCall.execute(inParams);
        return (List) m.get("result");

    }

    public List<PrescriptionReport> getPrescriptionReport() {

        procCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sart_proc_pharmacy_performance")
                //.withSchemaName("safari")
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("result", (rs, i) -> {
                    return mapRxResultRow(rs);
                });
        Map m = procCall.execute(new HashMap<String, Object>(0));
        return (List) m.get("result");
    }

    public List<OpenAttendanceDetails> getOpenAttendances() {

        procCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sart_proc_getopenattendances")
                //.withSchemaName("safari")
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("result", (rs, i) -> {
                    return mapOpenAttResultRow(rs);
                });
        Map m = procCall.execute(new HashMap<String, Object>(0));
        return (List) m.get("result");
    }

    public Map<String, Object> getDashBoardDetails() {

        procCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sart_proc_dashboard")
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("result", (rs, i) -> {
                    return new String[]{rs.getString(1), rs.getString(2)};
                });
        Map m = procCall.execute(new HashMap<String, Object>(0));
        List<String[]> list = (List) m.get("result");

        Map<String, Object> data = new TreeMap<>();
        list.stream().forEach(p -> data.put(p[0], p[1]));

        return data;
    }

    public List<ListOfValue> getListOfValue(String lovType, int attendanceID) {
        List<ListOfValue> list = new ArrayList<>();
        Map in = new HashMap();
        in.put("vlovtype", lovType);
        in.put("attendanceid", attendanceID);
        procCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sart_proc_getlistofvalues")
                .withoutProcedureColumnMetaDataAccess()
                .useInParameterNames("vlovType", "attendanceID")
                .declareParameters(new SqlParameter("vlovtype", Types.VARCHAR), new SqlParameter("attendanceid", Types.INTEGER))
                .returningResultSet("result", (rs, i) -> {
                    return mapLovResultRow(rs);
                });
        SqlParameterSource inParams = new MapSqlParameterSource(in);

        Map m = procCall.execute(inParams);
        return (List) m.get("result");
    }

    public List<PaymentSummary> getDailySummary() {
        List<PaymentSummary> list = new ArrayList<>();

        procCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sart_proc_daily_summaries")
                .withoutProcedureColumnMetaDataAccess()
                .returningResultSet("result", (rs, i) -> {
                    return mapResultRow(rs);
                });

        Map m = procCall.execute(new HashMap<String, Object>(0));
        return (List) m.get("result");

    }

    private PrescriptionReport mapRxResultRow(ResultSet rs) throws SQLException {
        PrescriptionReport ps = new PrescriptionReport();
        ps.setDrugName(rs.getString("drugName"));
        ps.setChargeAmount(rs.getDouble("chargeAmount"));
        ps.setCreated(rs.getDate("created"));
        ps.setQuantity(rs.getInt("quantity"));
        ps.setDispensed(rs.getBoolean("dispensed"));

        return ps;
    }

    private OpenAttendanceDetails mapOpenAttResultRow(ResultSet rs) throws SQLException {
        OpenAttendanceDetails att = new OpenAttendanceDetails();
        att.setAttendanceID(rs.getInt("attendanceID"));
        att.setPatientID(rs.getInt("patientID"));
        att.setAttendanceNumber(rs.getString("attendanceNumber"));
        att.setAttendanceStatus(rs.getString("attendanceStatus"));
        att.setAttendanceType(rs.getString("attendanceType"));
        att.setCreated(rs.getDate("created"));
        att.setPatientName(rs.getString("patientName"));
        att.setPaymentMode(rs.getString("paymentMode"));
        att.setInsuranceNumber(rs.getString("insuranceNumber"));
        return att;
    }

    private ChargeDisplay mapChargeMatrixResultRow(ResultSet rs) throws SQLException {
        ChargeDisplay cd = new ChargeDisplay();
        cd.setAmount(rs.getDouble("amount"));
        cd.setChargeid(rs.getInt("chargeid"));
        cd.setLovID(rs.getInt("lovID"));
        cd.setLovName(rs.getString("lovName"));
        cd.setLovVal(rs.getString("lovVal"));
        cd.setLovtype(rs.getString("lovtype"));
        cd.setPaymentMode(rs.getInt("paymentMode"));
        cd.setRecordStatus(rs.getString("recordStatus"));
        return cd;
    }

    private PaymentSummary mapResultRow(ResultSet rs) throws SQLException {
        PaymentSummary ps = new PaymentSummary();
        ps.setName(rs.getString("pt_name"));
        ps.setPay(rs.getDouble("totalpay"));
        ps.setPaydate(rs.getDate("paydate"));
        ps.setRowID(rs.getInt("rowid"));

        return ps;
    }

    public ListOfValue mapLovResultRow(ResultSet rs) throws SQLException {
        ListOfValue lov = new ListOfValue();
        lov.setLovID(rs.getInt("lovID"));
        lov.setRowID(rs.getInt("rowID"));
        lov.setLovName(rs.getString("lovName"));
        lov.setLovVal(rs.getString("lovVal"));

        return lov;
    }
}
