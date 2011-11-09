package filet

class Blob {
    String hash
    boolean uploadComplete = false
    long size
    def data
    
    static belongsTo = DataStore
    
    static constraints = {
    }
}
