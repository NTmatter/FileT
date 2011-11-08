package filet

/**
 * Placeholder for a real user class backed by Spring Security
 */
class FiletUser {
    String name
    String email
    boolean internal
    
    static constraints = {
        name blank: false
        email email: true
    }
}