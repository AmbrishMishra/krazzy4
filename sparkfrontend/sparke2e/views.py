#!/usr/bin/env python
# -*- coding: utf-8 -*-

import requests
import json
from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
from django.conf import settings as djangoSettings
from datetime import datetime
import time


# def validation_page(request):
#     return render(request, 'sparkfrontend.html')

def images_to_video(request):
    return render(request, 'spark.html')

def generate_video(request):
    information = str(request.POST.get('info',''));
    json_info = json.loads(information)

    print json_info
    audiourl = json_info['audiourl'];
    campaignname = json_info['campaignname'];
    numberofimages = json_info['numberofimages'];

    #print json_info['assetsJsonArray'][0]['weight'];

    asset = {};
    response = {};
    imageAssetCombined = []
    campaignAudio=str(campaignname)+'$'+str(audiourl)
    imageAssetCombined.append(campaignAudio);


    fixedUrl="http://localhost:8000/static/html/"
    portraiturlfinal ='';
    landscapeurlfinal ='';
    try:
        for asset in json_info['assetsJsonArray']:
            millis = int(round(time.time() * 1000))
            portraitfilename = 'portrait/'+str(campaignname)+str(millis)
            landscapefilename = 'landscape/'+str(campaignname)+str(millis)
            file=open(djangoSettings.STATIC_ROOT_HTML+portraitfilename+'.html','w');
            file.write(asset['portraithtml']);

            file.close()
            file=open(djangoSettings.STATIC_ROOT_HTML+landscapefilename+'.html','w');
            file.write(asset['landscapehtml']);
            file.close()
            weight = asset['weight'];
            portraiturl = fixedUrl + portraitfilename + '.html'
            landscapeurl = fixedUrl + landscapefilename + '.html'
            print portraiturl
            print landscapeurl
            portraiturlfinal = portraiturlfinal+'$'+portraiturl+'^'+weight
            landscapeurlfinal =  landscapeurlfinal +'$'+landscapeurl+'^'+weight
            print portraiturlfinal
            print landscapeurlfinal
            # imageasset = '$'+portraiturl+'^'+weight
            # imageAssetCombined.append(imageasset);
        # print portraiturlfinal
        # print landscapeurlfinal

        finalPortraitUrl = campaignname+'$'+audiourl+portraiturlfinal
        finalLandscapeUrl = campaignname+'$'+audiourl+landscapeurlfinal

        finalInput = finalPortraitUrl + '#'+ finalLandscapeUrl
        #''.join(imageAssetCombined);
        print finalInput

        # json_data = json.dumps(data)
        headers = {'content-type': 'application/json'}
        url = 'http://localhost:9099/Spark/api/getVideoFile'

        response = requests.post(url, data=finalInput, headers=headers)

        print response
        return HttpResponse(response, content_type='application/json', status=200)
    except Exception as e:
        print 'exception : ',e

        return HttpResponse(e, content_type='application/json', status=500)
