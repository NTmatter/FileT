package filet

class Batch {
    
    String subject
    String message
    FiletUser sender
    Date expiration
    
    static hasMany = [ recipients: FiletUser, contents: TransferrableBlob ]
    
    static constraints = {
        subject maxSize: 255, blank: false, nullable: false
        message maxSize: 65535, nullable: false
    }
}
