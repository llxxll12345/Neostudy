package org.example.cards.utils;

import com.google.api.services.vision.v1.model.*;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.AsyncAnnotateFileRequest;
import com.google.cloud.vision.v1.AsyncAnnotateFileResponse;
import com.google.cloud.vision.v1.AsyncBatchAnnotateFilesResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.ColorInfo;
import com.google.cloud.vision.v1.CropHint;
import com.google.cloud.vision.v1.CropHintsAnnotation;
import com.google.cloud.vision.v1.DominantColorsAnnotation;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.GcsDestination;
import com.google.cloud.vision.v1.GcsSource;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Detector {
    public static ArrayList<String> detectLabels(String filePath, PrintStream out) throws Exception, IOException {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));
        System.out.println(imgBytes);
        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    out.printf("Error: %s\n", res.getError().getMessage());
                    return null;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                    annotation.getAllFields().forEach((k, v) -> out.printf("%s : %s\n", k, v.toString()));
                    Set<Descriptors.FieldDescriptor> sets = annotation.getAllFields().keySet();
                    for (Descriptors.FieldDescriptor ss : sets) {
                        if (ss.toString().
                                equals("google.cloud.vision.v1.EntityAnnotation.description")) {
                            result.add(annotation.getAllFields().get(ss).toString());
                        }
                        //System.out.println(ss.toString());
                        //result.add(ss.toString());
                    }
                }
            }

        }
        return result;
    }
}
