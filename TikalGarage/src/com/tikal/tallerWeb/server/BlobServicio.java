package com.tikal.tallerWeb.server;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class BlobServicio {

	public static String urlUpld = "";

	public BlobServicio() {
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		String uri = blobstoreService.createUploadUrl("/servicio/uploadFile");
//		uri= uri.substring(uri.indexOf("/")+1);
//		uri= uri.substring(uri.indexOf("/")+1);
//		uri= uri.substring(uri.indexOf("/"));
		BlobServicio.urlUpld=uri;
	}
}
