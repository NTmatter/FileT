package filet

class Batch {
    
    String subject
    String message
    String sender
    Date expiration
    
    static hasMany = [ recipients: String, contents: TransferrableBlob ]
    
    static constraints = {
        subject maxSize: 255, blank: false, nullable: false
        message maxSize: 65535, nullable: false
    }
}
