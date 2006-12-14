
package net.abbht.lamo.persistence.users ;
/**
 * 
 *
 * @hibernate.class
 *     table="PHONE_TYPES"
 *
 */
public class PhoneTypes {
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String type ">
  private String type;
/**
  *   @hibernate.property
  */
   public String getType () {
      return type;
   } 
   public void setType (String type) {
      this.type = type;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String Description ">
  private String Description;
/**
  *   @hibernate.property
  */
   public String getDescription () {
      return Description;
   } 
   public void setDescription (String Description) {
      this.Description = Description;
   }
   // </editor-fold>

   // <editor-fold defaultstate="collapsed" desc=" PrimaryKey:   Long id ">
   private Long id;
/**
  *   @hibernate.id
  *     generator-class="increment"
  */
   public Long getId () {
      return id;
   } 
   public void setId (Long id) {
      this.id = id;
   }
   //</editor-fold>
}