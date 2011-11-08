package filet

class TransferrableBlob {
    String path
    Blob contents
    
    static constraints = {
        contents nullable: false
        path nullable: false, blank: false
    }
}