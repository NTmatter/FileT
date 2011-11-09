import filet.FiletRole

class BootStrap {

    def init = { servletContext ->
        def senderRole = FiletRole.findByAuthority('ROLE_SENDER') ?: new FiletRole(authority: 'ROLE_SENDER').save(failOnError: true)
        def receiverRole = FiletRole.findByAuthority('ROLE_RECEIVER') ?: new FiletRole(authority: 'ROLE_RECEIVER').save(failOnError: true)
        def adminRole = FiletRole.findByAuthority('ROLE_ADMIN') ?: new FiletRole(authority: 'ROLE_ADMIN').save(failOnError: true)
    }
    def destroy = {
    }
}
