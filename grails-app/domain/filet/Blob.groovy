package filet

class Blob {
    String hash
    def data
    
    static belongsTo = DataStore
    
    static constraints = {
    }
}
