/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary.List;

import java.util.Set;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Rith Record
 */
public class ListClass {
    
    private StringProperty KeywordinKhmer;
    private StringProperty KeywordinEnglish;
    private StringProperty KeywordinFrance;
    private StringProperty Read;
    private StringProperty Wordclass;
    private StringProperty Committee;
    private StringProperty Major;
    private StringProperty Date;

    public ListClass(String KeywordinKhmer, String KeywordinEnglish, String KeywordinFrance, String Read, String Wordclass, String Committee, String Major, String Date) {
        this.KeywordinKhmer = new SimpleStringProperty(KeywordinKhmer);
        this.KeywordinEnglish = new SimpleStringProperty(KeywordinEnglish);
        this.KeywordinFrance = new SimpleStringProperty(KeywordinFrance);
        this.Read = new SimpleStringProperty(Read);
        this.Wordclass = new SimpleStringProperty(Wordclass);
        this.Committee = new SimpleStringProperty(Committee);
        this.Major = new SimpleStringProperty(Major);
        this.Date = new SimpleStringProperty(Date);
    }

    public String getKeywordinKhmer() {
        return KeywordinKhmer.get();
    }

    public StringProperty KeywordinKhmerProperty() {
        return KeywordinKhmer;
    }

    public void setKeywordinKhmer(String KeywordinKhmer) {
        this.KeywordinKhmer.set(KeywordinKhmer);
    }
    
    public String getKeywordinEnglish() {
        return KeywordinEnglish.get();
    }
    public StringProperty KeywordinEnglishProperty() {
        return KeywordinEnglish;
    }

    public void setKeywordinEnglish(String KeywordinEnglish) {
        this.KeywordinEnglish.set(KeywordinEnglish);
    }
    
     public String getKeywordinFrance() {
        return KeywordinFrance.get();
    }

    public StringProperty KeywordinFranceProperty() {
        return KeywordinFrance;
    }

    public void setKeywordinFrance(String KeywordinFrance) {
        this.KeywordinFrance.set(KeywordinFrance);
    }
    
    public String getCommittee(){
        
        return Committee.get();
    }

    public StringProperty CommitteeProperty() {
        return Committee;
    }

    public void setCommittee(String Committee) {
        this.Committee.set(Committee);
    }
    
    public String getRead() {
        return Read.get();
    }

    public StringProperty ReadProperty() {
        return Read;
    }
     public void setRead(String Read) {
        this.Read.set(Read);
    }

    public String getWordclass() {
        return Wordclass.get();
    }

    public StringProperty WordclassProperty() {
        return Wordclass;
    }

    public void setWordclass(String Wordclass) {
        this.Wordclass.set(Wordclass);
    }
    
    public String getMajor() {
        return Major.get();
    }

    public StringProperty MajoProperty() {
        return Major;
    }

    public void setMajor(String Major) {
        this.Major.set(Major);
    }
    
    public String getDate() {
        return Date.get();
    }

    public StringProperty DateProperty() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date.set(Date);
    }
}