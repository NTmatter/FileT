package filet

class DataStore {
    String name
    String description
    
    static hasMany = [ data: Blob ]
    
    static constraints = {
        name blank: false
    }
}