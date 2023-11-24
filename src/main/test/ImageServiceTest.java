package test;

import com.underlake.honey.service.ImageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageServiceTest {

    ImageService imageService = new ImageService();

    @Test
    public void testAssetNameParsing() {
        String publicPath = givenPublicPath();

        String assetName = imageService.getAssetNameByPublicPath(publicPath);

        Assertions.assertEquals(givenAssetName(), assetName);
    }

    @Test
    public void testAssetPublicPathComposition() {
        String publicPath = givenAssetName();

        String assetName = imageService.getAssetPublicPath(publicPath);

        Assertions.assertEquals(givenPublicPath(), assetName);
    }

    private String givenPublicPath() {
        return "https://elasticbeanstalk-eu-central-1-306070261283.s3.eu-central-1.amazonaws.com/public/news1.jpg";
    }

    private String givenAssetName() {
        return "news1.jpg";
    }
}
