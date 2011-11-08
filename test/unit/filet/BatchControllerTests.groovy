package filet



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(BatchController)
@Mock(Batch)
class BatchControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/batch/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.batchInstanceList.size() == 0
        assert model.batchInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.batchInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.batchInstance != null
        assert view == '/batch/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/batch/show/1'
        assert controller.flash.message != null
        assert Batch.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/batch/list'


        populateValidParams(params)
        def batch = new Batch(params)

        assert batch.save() != null

        params.id = batch.id

        def model = controller.show()

        assert model.batchInstance == batch
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/batch/list'


        populateValidParams(params)
        def batch = new Batch(params)

        assert batch.save() != null

        params.id = batch.id

        def model = controller.edit()

        assert model.batchInstance == batch
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/batch/list'

        response.reset()


        populateValidParams(params)
        def batch = new Batch(params)

        assert batch.save() != null

        // test invalid parameters in update
        params.id = batch.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/batch/edit"
        assert model.batchInstance != null

        batch.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/batch/show/$batch.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        batch.clearErrors()

        populateValidParams(params)
        params.id = batch.id
        params.version = -1
        controller.update()

        assert view == "/batch/edit"
        assert model.batchInstance != null
        assert model.batchInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/batch/list'

        response.reset()

        populateValidParams(params)
        def batch = new Batch(params)

        assert batch.save() != null
        assert Batch.count() == 1

        params.id = batch.id

        controller.delete()

        assert Batch.count() == 0
        assert Batch.get(batch.id) == null
        assert response.redirectedUrl == '/batch/list'
    }
}
