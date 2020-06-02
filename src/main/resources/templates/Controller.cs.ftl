using AutoMapper;
using Luxshare.Api.Entities;
using Luxshare.Api.Entities.Enums;
using Luxshare.Api.Extensions;
using Luxshare.Api.Extensions.AuthContext;
using Luxshare.Api.Extensions.CustomException;
using Luxshare.Api.Models.Response;
using Luxshare.Api.RequestPayload.Rbac.Menu;
using Luxshare.Api.ViewModels.Rbac.DncMenu;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Data.SqlClient;

namespace Luxshare.Api.Controllers.Api.V1.Rbac
{
    /// <summary>
    /// 
    /// </summary>
    //[CustomAuthorize]
    [Route("api/v1/rbac/[controller]/[action]")]
    [ApiController]
    [CustomAuthorize]
    public class ${entityName}Controller : ControllerBase
    {
        private readonly MasterDbContext _masterContext;
        private readonly IMapper _mapper;

        /// <summary>
        /// 
        /// </summary>
        /// <param name="dbContext"></param>
        /// <param name="mapper"></param>
        public ${entityName}Controller(MasterDbContext masterContext, IMapper mapper)
        {
            _masterContext = masterContext;
            _mapper = mapper;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        [HttpPost]
        public IActionResult List(${entityName}RequestPayload payload)
        {
            using (_masterContext)
            {
                var query = _masterContext.${entityName}.AsQueryable();
                if (!string.IsNullOrEmpty(payload.Kw))
                {
                    query = query.Where(x => x.Name.Contains(payload.Kw.Trim()) || x.Url.Contains(payload.Kw.Trim()));
                }
                if (payload.IsDeleted > CommonEnum.IsDeleted.All)
                {
                    query = query.Where(x => x.IsDeleted == payload.IsDeleted);
                }
                if (payload.Status > CommonEnum.Status.All)
                {
                    query = query.Where(x => x.Status == payload.Status);
                }
                if (payload.ParentGuid.HasValue)
                {
                    query = query.Where(x => x.ParentGuid == payload.ParentGuid);
                }
                var list = query.Paged(payload.CurrentPage, payload.PageSize).ToList();
                var totalCount = query.Count();
                var data = list.Select(_mapper.Map<${entityName}, ${entityName}JsonModel>);
                var response = ResponseModelFactory.CreateResultInstance;
                response.SetData(data, totalCount);
                return Ok(response);
            }
        }

        /// <summary>
        /// 创建菜单
        /// </summary>
        /// <param name="model">菜单视图实体</param>
        /// <returns></returns>
        [HttpPost]
        [ProducesResponseType(200)]
        public IActionResult Create(${entityName}CreateViewModel model)
        {
            using (_masterContext)
            {
                var entity = _mapper.Map<${entityName}CreateViewModel, ${entityName}>(model);
                entity.Guid = Guid.NewGuid();
            <#list columns as column>
                entity.${column.propertyName} = model.${column.propertyName};
            </#list>
                entity.CreatedOn = DateTime.Now;
                entity.CreatedByUserGuid = AuthContextService.CurrentUser.Guid;
                entity.CreatedByUserName = AuthContextService.CurrentUser.DisplayName;
                entity.IsDeleted = CommonEnum.IsDeleted.No;
                _masterContext.${entityName}.Add(entity);
                _masterContext.SaveChanges();
                var response = ResponseModelFactory.CreateInstance;
                response.SetSuccess();
                return Ok(response);
            }
        }

        /// <summary>
        /// 编辑菜单
        /// </summary>
        /// <param name="guid">菜单ID</param>
        /// <returns></returns>
        [HttpGet("{guid}")]
        [ProducesResponseType(200)]
        public IActionResult Edit(Guid guid)
        {
            using (_masterContext)
            {
                var entity = _masterContext.${entityName}.FirstOrDefault(x => x.Guid == guid);
                var response = ResponseModelFactory.CreateInstance;
                var model = _mapper.Map<${entityName}, ${entityName}EditViewModel>(entity);
                //if (model.ParentGuid.HasValue)
                //{
                //    var parent = _dbContext.${entityName}.FirstOrDefault(x => x.Guid == entity.ParentGuid);
                //    if (parent != null)
                //    {
                //        model.ParentName = parent.Name;
                //    }
                //}
                var tree = Load${entityName}Tree(model.ParentGuid.ToString());
                response.SetData(new { model, tree });
                return Ok(response);
            }
        }

        /// <summary>
        /// 保存编辑后的菜单信息
        /// </summary>
        /// <param name="model">菜单视图实体</param>
        /// <returns></returns>
        [HttpPost]
        [ProducesResponseType(200)]
        public IActionResult Edit(${entityName}EditViewModel model)
        {
            using (_masterContext)
            {
                var entity = _masterContext.${entityName}.FirstOrDefault(x => x.Guid == model.Guid);

            <#list columns as column>
                entity.${column.propertyName} = model.${column.propertyName};
            </#list>

                entity.ModifiedByUserGuid = AuthContextService.CurrentUser.Guid;
                entity.ModifiedByUserName = AuthContextService.CurrentUser.DisplayName;
                entity.ModifiedOn = DateTime.Now;

                _masterContext.SaveChanges();
                var response = ResponseModelFactory.CreateInstance;
                response.SetSuccess();
                return Ok(response);
            }
        }

    }

}