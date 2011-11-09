import grails.util.Environment
import filet.FiletUser
import filet.FiletRole
import filet.FiletUserFiletRole

class BootStrap {

    def init = { servletContext ->
        def senderRole = FiletRole.findByAuthority('ROLE_SENDER') ?: new FiletRole(authority: 'ROLE_SENDER').save(failOnError: true)
        def receiverRole = FiletRole.findByAuthority('ROLE_RECEIVER') ?: new FiletRole(authority: 'ROLE_RECEIVER').save(failOnError: true)
        def adminRole = FiletRole.findByAuthority('ROLE_ADMIN') ?: new FiletRole(authority: 'ROLE_ADMIN').save(failOnError: true)
        
        Environment.executeForCurrentEnvironment {
            development {
                def test1 = FiletUser.findByUsername('test1@example.com') ?: new FiletUser(username: 'test1@example.com', password: 'pw', enabled: true).save(failOnError: true)
                if(!test1.authorities.contains(senderRole)) {
                    FiletUserFiletRole.create(test1, senderRole)
                }
                
                def test2 = FiletUser.findByUsername('test2@not.example.com') ?: new FiletUser(username: 'test2@not.example.com', password: 'pw', enabled: true).save(failOnError: true)
                
                if(!test2.authorities.contains(receiverRole)) {
                    FiletUserFiletRole.create(test2,receiverRole)
                }
            }
        }
    }
    
    def destroy = {
    }
}
