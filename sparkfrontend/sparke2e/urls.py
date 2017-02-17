import os
import logging
from django.conf.urls import url
from django.views.static import serve
from django.contrib import admin
from . import views

logger = logging.getLogger(__name__)

BASE_PATH = os.path.abspath(os.path.dirname(__file__))
STATIC_RESOURCE_PATH = os.path.join(BASE_PATH, '/static');

logger.info('static path is %s', STATIC_RESOURCE_PATH)

urlpatterns = [
    url(r'^imagestovideo/', views.images_to_video, name='images_to_video'),
    url(r'^generatevideo/', views.generate_video, name='generate_video'),
    url(r'^static/(?P<path>.*)$', serve, {'document_root': STATIC_RESOURCE_PATH})
]